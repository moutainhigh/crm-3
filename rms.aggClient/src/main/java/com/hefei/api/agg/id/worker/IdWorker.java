package com.hefei.api.agg.id.worker;

/**
 * tweeter的snowflake 移植到Java:
 *   (a) id构成: 42位的时间前缀 + 10位的节点标识 + 12位的sequence避免并发的数字(12位不够用时强制得到新的时间前缀)
 *       10位节点标识：5位的datacenter加5位的机器id;
 *   (b) 对系统时间的依赖性非常强，需关闭ntp的时间同步功能。当检测到ntp时间调整后，将会拒绝分配id
 */
public class IdWorker {
 
  private final long twepoch = 1288834974657L;// 时间起始标记点，作为基准，一般取系统的最近时间
  private final long workerIdBits = 5L;// 机器标识位数
  private final long datacenterIdBits = 5L;//物理机房标识位数
  private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
  private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
  private final long sequenceBits = 12L;
  private final long workerIdShift = sequenceBits;
  private final long datacenterIdShift = sequenceBits + workerIdBits;
  private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
  private final long sequenceMask = -1L ^ (-1L << sequenceBits);
 
  private long workerId;//机器id
  private long datacenterId;//物理机房id
  private long sequence = 0L;
  private long lastTimestamp = -1L;
 
  public IdWorker(long workerId, long datacenterId) {
    if (workerId > maxWorkerId || workerId < 0) {
      throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
    }
    if (datacenterId > maxDatacenterId || datacenterId < 0) {
      throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
    }
    this.workerId = workerId;
    this.datacenterId = datacenterId;
  }
 
  public synchronized long nextId() {
    long timestamp = timeGen();
    if (timestamp < lastTimestamp) {
      throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
    }
    if (lastTimestamp == timestamp) {// 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
      sequence = (sequence + 1) & sequenceMask;
      if (sequence == 0) {
        timestamp = tilNextMillis(lastTimestamp);// 重新生成timestamp
      }
    } else {
      sequence = 0L;
    }
 
    lastTimestamp = timestamp;
 
    return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
  }
  /**
   * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
   */
  protected long tilNextMillis(long lastTimestamp) {
    long timestamp = timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = timeGen();
    }
    return timestamp;
  }
  /**
   * 获得系统当前毫秒数
   */
  protected long timeGen() {
    return System.currentTimeMillis();
  }
 
  //test
  public static void main(String[] args) {
    IdWorker idWorker = new IdWorker(0, 0);
    for (int i = 0; i < 1000; i++) {
      long id = idWorker.nextId();
      System.out.println(id);
    }
  }
}
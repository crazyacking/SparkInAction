package com.hut.demo.spark;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

/**
 * Created by crazyacking on 2017/1/1.
 */
public class SampleMapper implements Mapper<LongWritable, Text, LongWritable, Text> {
    public SampleMapper() {
    }

    public void configure(JobConf jobConf) {
    }

    public void map(LongWritable key, Text value, OutputCollector<LongWritable, Text> collector, Reporter reporter) throws IOException {
        collector.collect(key, value);
    }

    public void close() throws IOException {
    }
}

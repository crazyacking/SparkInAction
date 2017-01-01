package com.hut.demo.spark;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by crazyacking on 2017/1/1.
 */
public class SampleReducer implements Reducer<LongWritable, Text, LongWritable, Text> {
    public SampleReducer() {
    }

    public void configure(JobConf jobConf) {
    }

    public void reduce(LongWritable key, Iterator<Text> values, OutputCollector<LongWritable, Text> collector, Reporter reporter) throws IOException {
        while (values.hasNext()) {
            collector.collect(key, values.next());
        }

    }

    public void close() throws IOException {
    }
}

package com.hut.demo.spark;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.oozie.action.hadoop.OozieActionConfigurator;
import org.apache.oozie.action.hadoop.OozieActionConfiguratorException;

/**
 * Created by crazyacking on 2017/1/1.
 */

public class SampleOozieActionConfigurator implements OozieActionConfigurator {
    public SampleOozieActionConfigurator() {
    }

    public void configure(JobConf actionConf) throws OozieActionConfiguratorException {
        if (actionConf.getUser() == null) {
            throw new OozieActionConfiguratorException("No user set");
        } else if (actionConf.get("examples.root") == null) {
            throw new OozieActionConfiguratorException("examples.root not set");
        } else if (actionConf.get("output.dir.name") == null) {
            throw new OozieActionConfiguratorException("output.dir.name not set");
        } else {
            actionConf.setMapperClass(SampleMapper.class);
            actionConf.setReducerClass(SampleReducer.class);
            actionConf.setNumMapTasks(1);
            FileInputFormat.setInputPaths(actionConf, new Path("/user/" + actionConf.getUser() + "/" + actionConf.get("examples.root") + "/input-data/text"));
            FileOutputFormat.setOutputPath(actionConf, new Path("/user/" + actionConf.getUser() + "/" + actionConf.get("examples.root") + "/output-data/" + actionConf.get("output.dir.name")));
        }
    }
}

package io.jenkins.plugins.pipelinegraphview.utils;

import org.jenkinsci.plugins.workflow.pipelinegraphanalysis.TimingInfo;

public class AbstractPipelineNode {
    private String name;
    private PipelineState state;
    private String type; // TODO enum
    private String title;
    private String id;
    private long rawPauseDurationMillis;
    private long rawTotalDurationMillis;
    private long rawStartTimeMillis;
    private long executorAssignedTime;
    private long executorWaitTime;
    private long executorEndTime;
    private String pauseDurationMillis;
    private String totalDurationMillis;
    private TimingInfo timingInfo;

    public AbstractPipelineNode(
            String id,
            String name,
            PipelineState state,
            String type,
            String title,
            TimingInfo timingInfo) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.type = type;
        this.title = title;
        this.timingInfo = timingInfo;
        // These values won't change for a given TimingInfo.
        this.rawPauseDurationMillis = timingInfo.getPauseDurationMillis();
        this.rawTotalDurationMillis = timingInfo.getTotalDurationMillis();
        this.rawStartTimeMillis = timingInfo.getStartTimeMillis();
        this.pauseDurationMillis = getUserFriendlyPauseDuration(timingInfo.getPauseDurationMillis());
        this.totalDurationMillis = getUserFriendlyDuration(timingInfo.getTotalDurationMillis());
    }

    private String getUserFriendlyPauseDuration(long millis) {
        if (millis < 1) {
            return "";
        }
        return hudson.Util.getTimeSpanString(millis);
    }

    private String getUserFriendlyDuration(long millis) {
        if (millis < 1) {
            return "";
        }
        return hudson.Util.getTimeSpanString(millis);
    }

    public long getStartTimeMillis() {
        return timingInfo.getStartTimeMillis();
    }

    public long getRawStartTimeMillis() {
        // Dynamically generate as it depends of the current time.
        return rawStartTimeMillis;
    }

    public long getExecutorAssignedTime() {
        return executorAssignedTime;
    }

    public void setExecutorAssignedTime(long executorAssignedTime) {
        this.executorAssignedTime = executorAssignedTime;
    }

    public long getExecutorWaitTime() {
        return executorWaitTime;
    }

    public void setExecutorWaitTime(long executorWaitTime) {
        this.executorWaitTime = executorWaitTime;
    }

    public long getExecutorEndTime() {
        return executorEndTime;
    }

    public void setExecutorEndTime(long executorEndTime) {
        this.executorEndTime = executorEndTime;
    }

    public long getRawPauseDurationMillis() {
        return rawPauseDurationMillis;
    }

    public long getRawTotalDurationMillis() {
        return rawTotalDurationMillis;
    }

    public String getTotalDurationMillis() {
        return totalDurationMillis;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PipelineState getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    protected TimingInfo getTimingInfo() {
        return this.timingInfo;
    }
}

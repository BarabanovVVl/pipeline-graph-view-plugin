package io.jenkins.plugins.pipelinegraphview.utils;

import io.jenkins.plugins.pipelinegraphview.Messages;
import java.util.Collections;
import java.util.List;
import org.jenkinsci.plugins.workflow.pipelinegraphanalysis.TimingInfo;

class PipelineStageInternal {

    private String name;
    private List<String> parents;
    private PipelineState state;
    private FlowNodeWrapper.NodeType type;
    private String title;
    private String id;
    private String seqContainerName;
    private PipelineStageInternal nextSibling;
    private boolean sequential;
    private boolean synthetic;
    private TimingInfo timingInfo;
    private String agent;
    private long executorAssignedTime;
    private long executorWaitTime;
    private long executorEndTime;

    public PipelineStageInternal(
            String id,
            String name,
            List<String> parents,
            PipelineState state,
            FlowNodeWrapper.NodeType type,
            String title,
            boolean synthetic,
            TimingInfo times,
            String agent,
            long executorAssignedTime,
            long executorWaitTime,
            long executorEndTime) {
        this.id = id;
        this.name = name;
        this.parents = parents;
        this.state = state;
        this.type = type;
        this.title = title;
        this.synthetic = synthetic;
        this.timingInfo = times;
        this.agent = agent;
        this.executorAssignedTime = executorAssignedTime;
        this.executorWaitTime = executorWaitTime;
        this.executorEndTime = executorEndTime;
    }

    // Геттеры и сеттеры для новых полей
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

    public boolean isSequential() {
        return sequential;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSequential(boolean sequential) {
        this.sequential = sequential;
    }

    public void setState(PipelineState state) {
        this.state = state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNextSibling(PipelineStageInternal nextSibling) {
        this.nextSibling = nextSibling;
    }

    public void setSeqContainerName(String seqContainerName) {
        this.seqContainerName = seqContainerName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeqContainerName() {
        return seqContainerName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getParents() {
        return parents;
    }

    public PipelineState getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSynthetic() {
        return synthetic;
    }

    public void setSynthetic(boolean synthetic) {
        this.synthetic = synthetic;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String aAgent) {
        this.agent = aAgent;
    }

    public PipelineStage toPipelineStage(List<PipelineStage> children, String runUrl) {
        return new PipelineStage(
                id,
                name,
                children,
                state,
                type.name(),
                title,
                seqContainerName,
                nextSibling != null ? nextSibling.toPipelineStage(Collections.emptyList(), runUrl) : null,
                sequential,
                synthetic,
                synthetic && name.equals(Messages.FlowNodeWrapper_noStage()),
                timingInfo,
                agent,
                runUrl,
                executorAssignedTime,
                executorWaitTime,
                executorEndTime);
    }
}

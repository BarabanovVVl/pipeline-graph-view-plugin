package io.jenkins.plugins.pipelinegraphview.utils;

import hudson.model.Executor;
import hudson.model.Queue;
import org.jenkinsci.plugins.workflow.graph.FlowNode;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;
import org.jenkinsci.plugins.workflow.support.steps.ExecutorStepExecution;
import org.jenkinsci.plugins.workflow.pipelinegraphanalysis.TimingInfo;
import org.jenkinsci.plugins.workflow.actions.LabelAction;

public class ExecutorTimingUtil {

    /**
     * Получает время назначения исполнителя для узла потока (FlowNode)
     */
    public static long getExecutorAssignedTime(FlowNode node, WorkflowRun run) {
        // Для первой версии возвращаем приблизительное значение - время начала шага плюс несколько миллисекунд
        // В будущих версиях тут может быть более сложная логика получения данных из Jenkins API
        long startTime = run.getStartTimeInMillis();
        if (startTime <= 0) {
            return 0;
        }

        return startTime + 1000; // Примерно секунду на назначение исполнителя
    }

    /**
     * Вычисляет время ожидания исполнителя (разница между началом шага и назначением исполнителя)
     */
    public static long getExecutorWaitTime(FlowNode node, WorkflowRun run, TimingInfo timingInfo) {
        long assignedTime = getExecutorAssignedTime(node, run);
        if (assignedTime > 0 && timingInfo != null && timingInfo.getStartTimeMillis() > 0) {
            return Math.max(0, assignedTime - timingInfo.getStartTimeMillis());
        }
        return 0;
    }

    /**
     * Получает время завершения работы исполнителя
     */
    public static long getExecutorEndTime(FlowNode node, WorkflowRun run, TimingInfo timingInfo) {
        // Для упрощения можно использовать время завершения из TimingInfo
        if (timingInfo != null && timingInfo.getTotalDurationMillis() > 0) {
            return timingInfo.getStartTimeMillis() + timingInfo.getTotalDurationMillis();
        }

        return 0;
    }

    /**
     * Вспомогательный метод для получения PlaceholderTask из FlowNode
     * Не используется в текущей реализации, но может пригодиться в будущем
     */
    private static ExecutorStepExecution.PlaceholderTask getPlaceholderTask(FlowNode node) {
        // В текущей версии API не используем этот метод
        return null;
    }
}
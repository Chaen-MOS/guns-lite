package cn.enilu.guns.admin.runner;

import cn.enilu.guns.bean.vo.QuartzJob;
import cn.enilu.guns.service.task.JobService;
import org.junit.Test;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StartJobTest {

    @Test
    public void runAddsEachConfiguredQuartzJob() throws Exception {
        JobService jobService = mock(JobService.class);
        QuartzJob first = new QuartzJob();
        first.setJobName("one");
        QuartzJob second = new QuartzJob();
        second.setJobName("two");
        when(jobService.getTaskList()).thenReturn(Arrays.asList(first, second));
        StartJob startJob = new StartJob();
        ReflectionTestUtils.setField(startJob, "jobService", jobService);

        startJob.run(new DefaultApplicationArguments(new String[0]));

        verify(jobService).addJob(first);
        verify(jobService).addJob(second);
    }

    @Test
    public void runDoesNothingWhenNoJobsConfigured() throws Exception {
        JobService jobService = mock(JobService.class);
        when(jobService.getTaskList()).thenReturn(Collections.emptyList());
        StartJob startJob = new StartJob();
        ReflectionTestUtils.setField(startJob, "jobService", jobService);

        startJob.run(new DefaultApplicationArguments(new String[0]));

        verify(jobService, never()).addJob(org.mockito.ArgumentMatchers.any(QuartzJob.class));
    }
}

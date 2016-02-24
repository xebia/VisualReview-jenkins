package org.jenkinsci.plugins.visualreview;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.*;
import hudson.tasks.*;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.io.IOException;

@SuppressWarnings("UnusedDeclaration")
public class VisualReviewNotifier extends Notifier {

  @DataBoundConstructor
  public VisualReviewNotifier() {
  }

  public BuildStepMonitor getRequiredMonitorService() {
    return BuildStepMonitor.BUILD;
  }

  @Override
  public Action getProjectAction(AbstractProject<?, ?> project) {
    return new VisualReviewProjectAction(project);
  }

  @Override
  public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException {
    String url = VisualReviewUtil.extractVisualReviewReportURLFromLogs(build);
    if (url != null) {
      build.addAction(new VisualReviewBuildAction(url));
    }


    // build.setResult(Result.FAILURE);
    return true;
  }

  @Extension(ordinal = 1000)
  public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {

    @Override
    public String getDisplayName() {
      return Messages.VisualReviewNotifier_NotifierDescriptor_DisplayName();
    }


    @Override
    public boolean configure(StaplerRequest req, JSONObject json) {
      return true;
    }

    @Override
    public boolean isApplicable(Class<? extends AbstractProject> jobType) {
      return true;
    }
  }
}


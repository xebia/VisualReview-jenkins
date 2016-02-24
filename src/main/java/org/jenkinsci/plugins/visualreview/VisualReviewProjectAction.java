package org.jenkinsci.plugins.visualreview;

import hudson.PluginWrapper;
import hudson.model.AbstractProject;
import hudson.model.ProminentProjectAction;
import jenkins.model.Jenkins;

public class VisualReviewProjectAction implements ProminentProjectAction {
  private final AbstractProject<?, ?> project;

  public VisualReviewProjectAction(AbstractProject<?, ?> project) {
    this.project = project;
  }

  public String getIconFileName() {
    if (VisualReviewUtil.getLastSonarUrl(project) != null) {
      PluginWrapper wrapper = Jenkins.getInstance().getPluginManager()
          .getPlugin(VisualReviewPlugin.class);
      return "/plugin/" + wrapper.getShortName() + "/images/vrbadge.png";
    } else {
      return null;
    }
  }

  public String getDisplayName() {
    return Messages.VisualReviewProjectAction_ProjectAction_DisplayName();
  }

  public String getUrlName() {
    return VisualReviewUtil.getLastSonarUrl(project);
  }


}

package org.jenkinsci.plugins.visualreview;

import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.util.RunList;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisualReviewUtil {
  public static final String URL_PATTERN_IN_LOGS = ".*" + Pattern.quote("Your results can be viewed at: ") + "(.*)";

  public static String extractVisualReviewReportURLFromLogs(AbstractBuild<?, ?> build) throws IOException {
    BufferedReader br = null;
    String url = null;
    try {
      br = new BufferedReader(build.getLogReader());
      String strLine;
      while ((strLine = br.readLine()) != null) {
        Pattern p = Pattern.compile(URL_PATTERN_IN_LOGS);
        Matcher match = p.matcher(strLine);
        if (match.matches()) {
          url = match.group(1);
        }
      }
    } finally {
      IOUtils.closeQuietly(br);
    }
    return url;
  }

  /**
   * Iterate previous build of this project and return the last Sonar URL
   */
  public static String getLastSonarUrl(AbstractProject<?, ?> project) {
    RunList<?> builds = project.getBuilds();
    for (Run<?, ?> run : builds) {
      VisualReviewBuildAction action = run.getAction(VisualReviewBuildAction.class);
      if (action != null) {
        return action.getUrlName();
      }
    }
    return null;
  }
}

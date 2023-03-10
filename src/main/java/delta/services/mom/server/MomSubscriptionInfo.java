package delta.services.mom.server;

import java.util.HashSet;
import java.util.Set;

/**
 * Gather information about a single subscription.
 * @author DAM
 */
public class MomSubscriptionInfo
{
  private Set<String> _topics;

  /**
   * Constructot.
   */
  public MomSubscriptionInfo()
  {
    _topics=new HashSet<String>();
  }

  /**
   * Add a topic.
   * @param topic Name of the topic to add.
   */
  public void addTopic(String topic)
  {
    _topics.add(topic);
  }

  /**
   * Remove a topic.
   * @param topic Name of the topic to remove.
   */
  public void removeTopic(String topic)
  {
   _topics.remove(topic);
  }

  /**
   * Indicates if this subscription has the given topic.
   * @param topic Name of the topic to use.
   * @return <code>true</code> if it does, <code>false</code> otherwise.
   */
  public boolean hasTopic(String topic)
  {
    return _topics.contains(topic);
  }
}

package delta.services.mom.server;

import java.util.HashSet;
import java.util.Set;

public class MomSubscriptionInfo
{
  private Set<String> _topics;

  public MomSubscriptionInfo()
  {
    _topics=new HashSet<String>();
  }

  public void addTopic(String topic)
  {
    _topics.add(topic);
  }

  public void removeTopic(String topic)
  {
   _topics.remove(topic);
  }

  public boolean hasTopic(String topic)
  {
    return _topics.contains(topic);
  }
}

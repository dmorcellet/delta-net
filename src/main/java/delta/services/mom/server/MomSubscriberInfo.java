package delta.services.mom.server;

import delta.services.mom.common.MomSubscriber;

public class MomSubscriberInfo
{
  private MomSubscriber _id;
  private MomSubscriptionInfo _subscriptionInfo;

  public MomSubscriberInfo(MomSubscriber id)
  {
    _id=id;
    _subscriptionInfo=new MomSubscriptionInfo();
  }

  public MomSubscriber getId()
  {
    return _id;
  }

  public MomSubscriptionInfo getSubscriptionInfo()
  {
    return _subscriptionInfo;
  }
}

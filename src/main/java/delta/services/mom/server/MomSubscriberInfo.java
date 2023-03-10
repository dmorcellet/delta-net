package delta.services.mom.server;

import delta.services.mom.common.MomSubscriber;

/**
 * Gather information about a single subscriber.
 * @author DAM
 */
public class MomSubscriberInfo
{
  private MomSubscriber _id;
  private MomSubscriptionInfo _subscriptionInfo;

  /**
   * Constructor.
   * @param id Subscriber.
   */
  public MomSubscriberInfo(MomSubscriber id)
  {
    _id=id;
    _subscriptionInfo=new MomSubscriptionInfo();
  }

  /**
   * Get the subscriber.
   * @return the subscriber.
   */
  public MomSubscriber getId()
  {
    return _id;
  }

  /**
   * Get the subscription info.
   * @return the subscription info.
   */
  public MomSubscriptionInfo getSubscriptionInfo()
  {
    return _subscriptionInfo;
  }
}

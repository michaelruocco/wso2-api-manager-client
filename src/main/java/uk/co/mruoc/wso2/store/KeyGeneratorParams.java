package uk.co.mruoc.wso2.store;

import uk.co.mruoc.wso2.store.addapplication.AddApplicationParams;
import uk.co.mruoc.wso2.store.addsubscription.AddSubscriptionParams;
import uk.co.mruoc.wso2.store.generateapplicationkey.GenerateApplicationKeyParams;
import uk.co.mruoc.wso2.store.removesubscription.RemoveSubscriptionParams;

public interface KeyGeneratorParams extends GenerateApplicationKeyParams, AddApplicationParams, AddSubscriptionParams, RemoveSubscriptionParams {

}

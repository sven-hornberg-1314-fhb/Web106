package api

import org.scribe.builder.api.TwitterApi

/**
 * Created by marcman on 26.01.14.
 */
public class TwitterApiExample extends TwitterApi {

    private static final String REQUEST_TOKEN_RESOURCE = "api.twitter.com/oauth/request_token";
    private static final String ACCESS_TOKEN_RESOURCE = "api.twitter.com/oauth/access_token";

    @Override
    public String getAccessTokenEndpoint()
    {
        return "https://" + ACCESS_TOKEN_RESOURCE;
    }

    @Override
    public String getRequestTokenEndpoint()
    {
        return "https://" + REQUEST_TOKEN_RESOURCE;
    }

}

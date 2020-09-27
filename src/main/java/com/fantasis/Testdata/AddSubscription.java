/*
 * Copyright (c) 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.fantasis.Testdata;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.model.*;
import com.fantasis.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

/**
 * Subscribe a user to a channel using the YouTube Data API (v3). Use
 * OAuth 2.0 for authorization.
 *
 * @author Ibrahim Ulukaya
 */
public class AddSubscription {

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;

    /**
     * Subscribe the user's YouTube account to a user-selected channel.
     *
     * @param args command line args (not used).
     */
    public static void main(String[] args) {

        // This OAuth 2.0 access scope allows for full read/write access to the
        // authenticated user's account.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        try {
            // Authorize the request.
            Credential credential = Auth.authorize(scopes, "addsubscription");

            // This object is used to make YouTube Data API requests.
            //youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName(
               //     "youtube-cmdline-addsubscription-sample").build();

            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName(
                    "AIzaSyCW1borbhDIIXAtsuw7mYFVix_3dUVq06c").build();

//            // We get the user selected channel to subscribe.
//            // Retrieve the channel ID that the user is subscribing to.
//            String channelId = getChannelId();
//            System.out.println("You chose " + channelId + " to subscribe.");
//
//            // Create a resourceId that identifies the channel ID.
//            ResourceId resourceId = new ResourceId();
//            resourceId.setChannelId(channelId);
//            resourceId.setKind("youtube#channel");
//
//            // Create a snippet that contains the resourceId.
//            SubscriptionSnippet snippet = new SubscriptionSnippet();
//            snippet.setResourceId(resourceId);
//
//            // Create a request to add the subscription and send the request.
//            // The request identifies subscription metadata to insert as well
//            // as information that the API server should return in its response.
//            Subscription subscription = new Subscription();
//            subscription.setSnippet(snippet);
//            YouTube.Subscriptions.Insert subscriptionInsert =
//                    youtube.subscriptions().insert("snippet,contentDetails", subscription);
//            Subscription returnedSubscription = subscriptionInsert.execute();
//
//
//            // Print information from the API response.
//            System.out.println("\n================== Returned Subscription ==================\n");
//            System.out.println("  - Id: " + returnedSubscription.getId());
//            System.out.println("  - Title: " + returnedSubscription.getSnippet().getTitle());

            // Define and execute the API request
            System.out.println("\n================== Returned My List subscription ==================\n");

            YouTube youtubeService = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName(
                            "youtube-analytics-api-report-example").build();


            YouTube.Channels.List request = youtubeService.channels()
                    .list("snippet,contentDetails,statistics");
            ChannelListResponse response = request.setId("UC6un658Fh6xKIi7DwJmO7iA").execute();
            System.out.println(response.getItems().get(0).getStatistics().getSubscriberCount());

//            YouTube.Search.List channels = youtube.search().list("id,contentDetails");
//
//            //ChannelListResponse response = request.setMySubscribers(true).execute();
//
//            channels.setKey("AIzaSyCW1borbhDIIXAtsuw7mYFVix_3dUVq06c");
//            channels.setType("video");
//            //channels.setForUsername("UCVdOmIx5g31wfOduSJzKMaA");
//            SearchListResponse channelListResponse = channels.execute();
//
//            List<SearchResult> items = channelListResponse.getItems();
//
//
//            System.out.println("  - total: " + items.size());
//
//            for (SearchResult item_Tmp : items){
//                System.out.println("  - Id: " + item_Tmp.getId());
//            }

//
//            YouTube.Videos.List list = youtube.videos().list("contentDetails");
//            list.setId("flE8H8k858Y");
//            list.setId("FoQiW0wuxI8");
//            Video v = list.execute().getItems().get(0);
//
//            long i_Count = v.getStatistics().getLikeCount().longValue();
//            i_Count = list.execute().getItems().get(0).getStatistics().getLikeCount().longValue();

        } catch (GoogleJsonResponseException e) {
            System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
    }

    /*
     * Prompt the user to enter a channel ID and return it.
     */
    private static String getChannelId() throws IOException {

        String channelId = "";

//        System.out.print("Please enter a channel id: ");
//        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
//        channelId = bReader.readLine();
//
//        if (channelId.length() < 1) {
            // If nothing is entered, defaults to "YouTube For Developers."
            channelId = "UCQpM25U6iqaRSO-SZxd5oDw";
        //}
        return channelId;
    }
}

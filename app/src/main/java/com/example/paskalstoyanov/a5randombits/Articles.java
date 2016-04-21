package com.example.paskalstoyanov.a5randombits;

/**
 * Created by paskalstoyanov on 19/04/16.
 */
public class Articles {


    private String body;
    private String imageUrl;
    private String title;

    public Articles() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Articles(String Title, String ImageUrl, String Body)
    {
        body = Body;
        title = Title;
        imageUrl = ImageUrl;
    }


    //@Override
    //public String toString() { return "Article{body='"+body+"', title='"+title+"', imageUrl="+imageUrl+"\'}"; }

}

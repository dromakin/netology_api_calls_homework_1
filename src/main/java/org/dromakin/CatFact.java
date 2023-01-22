/*
 * File:     CatFact
 * Package:  org.dromakin
 * Project:  netology_api_calls_homework_1
 *
 * Created by dromakin as 22.01.2023
 *
 * author - dromakin
 * maintainer - dromakin
 * version - 2023.01.22
 */

package org.dromakin;

public class CatFact {

    /*
    {
    "id": "5b4910ae0508220014ccfe90",
    "text": "Кошки могут слышать ультразвук и коммуницировать с дельфинами.",
    "type": "cat",
    "user": "Alex Petrov",
    "upvotes": 12
  },
     */

    private String id;

    private String text;

    private String type;

    private String user;

    private Integer upvotes;

    public Integer getUpvotes() {
        return upvotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CatFact{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}

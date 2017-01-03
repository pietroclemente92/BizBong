package gamesoftitalia.bizbong.adapters;

/**
 * Created by gamesoftitalia on 24/12/2016.
 */

public class ItemDataSpinner {

    String text;
    Integer imageId;

    public ItemDataSpinner(String text, Integer imageId){
        this.text=text;
        this.imageId=imageId;
    }

    public String getText(){
        return text;
    }

    public Integer getImageId(){
        return imageId;
    }
}


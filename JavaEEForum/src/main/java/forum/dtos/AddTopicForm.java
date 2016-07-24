package forum.dtos;

public class AddTopicForm {

    private String title;
    private String text;
    private int categoryID;
    private int authorID;


    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int catID) {
        this.categoryID = catID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

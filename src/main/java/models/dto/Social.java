package models.dto;

public class Social {

    private String id;
    private String name;
    private String link;
    private String icon;
    private String iconFont;

    public Social() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Social(String name, String link, String icon, String iconFont)
    {
        this.name = name;
        this.link = link;
        this.icon = icon;
        this.iconFont = iconFont;
    }

    public String getIconFont() {
        return iconFont;
    }

    public void setIconFont(String iconFont) {
        this.iconFont = iconFont;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
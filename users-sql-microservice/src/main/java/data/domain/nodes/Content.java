package data.domain.nodes;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Content {
    @Id
    private Long id;

    private String title;
    private String url;

    Set<Event> events;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

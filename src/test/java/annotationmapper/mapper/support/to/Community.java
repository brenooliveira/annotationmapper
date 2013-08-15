package annotationmapper.mapper.support.to;

import java.util.ArrayList;
import java.util.List;

public class Community {

    private List<Human> humans = new ArrayList<Human>();

    public List<Human> getHumans() {
        return humans;
    }

    public void setHumans(final List<Human> humans) {
        this.humans = humans;
    }

}

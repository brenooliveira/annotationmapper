package annotationmapper.mapper.support.adapter;

import annotationmapper.mapper.adapter.AdapterMapper;
import annotationmapper.mapper.support.from.Schooling;
import annotationmapper.mapper.support.to.Escolaridade;

public class SchoolingAdapterMapper implements AdapterMapper<Schooling, Escolaridade> {

    @Override
    public Escolaridade map(final Schooling resource) {
        switch (resource) {
            case GRADUATE:
                return Escolaridade.GRADUADO;
            case POSTGRADUATE:
                return Escolaridade.POS_GRADUADO;

            default:
                return null;

        }
    }
}

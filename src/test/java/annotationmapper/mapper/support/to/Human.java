package annotationmapper.mapper.support.to;

public class Human {

    public String fullname;
    public Integer ages;
    public String street;
    public Endereco endereco;
    public String sexo;
    public Escolaridade escolaridade;

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(final Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Human() {
    }

    public Human(final String fullname, final Integer ages) {
        this.fullname = fullname;
        this.ages = ages;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    public Integer getAges() {
        return ages;
    }

    public void setAges(final Integer ages) {
        this.ages = ages;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(final Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(final String sexo) {
        this.sexo = sexo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (fullname == null ? 0 : fullname.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Human other = (Human) obj;
        if (fullname == null) {
            if (other.fullname != null)
                return false;
        } else if (!fullname.equals(other.fullname))
            return false;
        return true;
    }

}

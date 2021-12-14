package org.koxx4.syntax;

import com.google.common.base.Objects;

public class UniqueToken extends Token{
    private final int id;

    public UniqueToken(String value, int id) {
        super(value);
        this.id = id;
    }

    public UniqueToken(SimpleSyntaxRegexType regexType, int id){
        super(regexType);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UniqueToken that = (UniqueToken) o;
        return (id == that.id)
                && getValue().equals(that.getValue())
                && (getRegexType() == that.getRegexType());
    }

    @Override
    public int hashCode() {
        return id;
    }
}

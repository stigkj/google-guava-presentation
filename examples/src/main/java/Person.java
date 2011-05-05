public class Person {
    final String name, nickname;
    final Movie favMovie;

    @Override
    public boolean equals(Object object) {
        if (object instanceof Person) {
            Person that = (Person) object;
            return Objects.equal(this.name, that.name)
                && Objects.equal(this.nickname, that.nickname)
                && Objects.equal(this.favMovie, that.favMovie);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, nickname, favMovie);
    }
}
package nerd.utopian.java.function.currying;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Function;

public class FunctionCurryingDemo {

    public static void main(String[] args) {

        SandwichOven sandwichOvenOperator = new SandwichOvenOperator();

        // performing apply() in the order of veggies -> eggs -> meat -> pepper -> onion, which is
        // defined in the SandwichOvenOperator.apply(bread) method.
        Sandwich sandwich = sandwichOvenOperator
                .apply("oregano") // for bread
                .apply(true) // for veggies
                .apply(true) // for eggs
                .apply(false) // for meat
                .apply(true) // for pepper
                .apply(false);// for onion

        System.out.println("Sandwich prepared: " + sandwich);
    }
}

interface SandwichOven extends
        Function<String,
                Function<Boolean,
                        Function<Boolean,
                                Function<Boolean,
                                        Function<Boolean,
                                                Function<Boolean, Sandwich>>>>>> {

}

class SandwichOvenOperator implements SandwichOven {

    @Override
    public Function<Boolean,
            Function<Boolean,
                    Function<Boolean,
                            Function<Boolean,
                                    Function<Boolean, Sandwich>>>>> apply(String bread) {
        return veggies -> eggs -> meat -> pepper -> onion -> Sandwich.builder()
                .setBread(bread)
                .setEggs(eggs)
                .setMeat(meat)
                .setOnion(onion)
                .setPepper(pepper)
                .setVeggies(veggies)
                .build();
    }
}

class Sandwich {

    private String bread;
    private boolean veggies;
    private boolean eggs;
    private boolean meat;
    private boolean pepper;
    private boolean onion;

    // region Constructor, getters, setters, equals, hashCode, toString, builder -- generated by BoB the Builder of Beans
    // The code below has been generated by BoB the Builder of Beans based on the class' fields.
    // Everything after this comment will be regenerated if you invoke BoB again.
    // If you don't know who BoB is, you can find him here: https://bitbucket.org/atlassianlabs/bob-the-builder-of-beans

    private Sandwich(String bread, boolean veggies, boolean eggs, boolean meat, boolean pepper, boolean onion) {
        this.bread = Objects.requireNonNull(bread);
        this.veggies = veggies;
        this.eggs = eggs;
        this.meat = meat;
        this.pepper = pepper;
        this.onion = onion;
    }

    // region Getters and setters -- generated by BoB the Builder of Beans
    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = Objects.requireNonNull(bread);
    }

    public boolean isVeggies() {
        return veggies;
    }

    public void setVeggies(boolean veggies) {
        this.veggies = veggies;
    }

    public boolean isEggs() {
        return eggs;
    }

    public void setEggs(boolean eggs) {
        this.eggs = eggs;
    }

    public boolean isMeat() {
        return meat;
    }

    public void setMeat(boolean meat) {
        this.meat = meat;
    }

    public boolean isPepper() {
        return pepper;
    }

    public void setPepper(boolean pepper) {
        this.pepper = pepper;
    }

    public boolean isOnion() {
        return onion;
    }

    public void setOnion(boolean onion) {
        this.onion = onion;
    }// endregion Getters and setters

    // region hashCode() and equals() -- generated by BoB the Builder of Beans
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Sandwich that = (Sandwich) o;

        return Objects.equals(this.getBread(), that.getBread()) && Objects.equals(this.isVeggies(), that.isVeggies()) && Objects.equals(this.isEggs(), that.isEggs()) && Objects.equals(this.isMeat(), that.isMeat()) && Objects.equals(this.isPepper(), that.isPepper()) && Objects.equals(this.isOnion(), that.isOnion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBread(), isVeggies(), isEggs(), isMeat(), isPepper(), isOnion());
    }// endregion hashCode() and equals()

    // region toString() -- generated by BoB the Builder of Beans
    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "{", "}")
                .add("bread=" + getBread())
                .add("veggies=" + isVeggies())
                .add("eggs=" + isEggs())
                .add("meat=" + isMeat())
                .add("pepper=" + isPepper())
                .add("onion=" + isOnion())
                .toString();
    }// endregion toString()

    // region Builder -- generated by BoB the Builder of Beans
    public static Sandwich.Builder builder() {
        return new Sandwich.Builder();
    }

    public static Sandwich.Builder builder(Sandwich data) {
        return new Sandwich.Builder(data);
    }

    public static final class Builder {

        private String bread;
        private boolean veggies;
        private boolean eggs;
        private boolean meat;
        private boolean pepper;
        private boolean onion;

        private Builder() {
        }

        private Builder(Sandwich initialData) {
            this.bread = initialData.bread;
            this.veggies = initialData.veggies;
            this.eggs = initialData.eggs;
            this.meat = initialData.meat;
            this.pepper = initialData.pepper;
            this.onion = initialData.onion;
        }

        public Builder setBread(String bread) {
            this.bread = bread;
            return this;
        }

        public Builder setVeggies(boolean veggies) {
            this.veggies = veggies;
            return this;
        }

        public Builder setEggs(boolean eggs) {
            this.eggs = eggs;
            return this;
        }

        public Builder setMeat(boolean meat) {
            this.meat = meat;
            return this;
        }

        public Builder setPepper(boolean pepper) {
            this.pepper = pepper;
            return this;
        }

        public Builder setOnion(boolean onion) {
            this.onion = onion;
            return this;
        }

        public Sandwich build() {
            return new Sandwich(bread, veggies, eggs, meat, pepper, onion);
        }
    }// endregion Builder
    // endregion Constructor, getters, setters, equals, hashCode, toString, builder
}

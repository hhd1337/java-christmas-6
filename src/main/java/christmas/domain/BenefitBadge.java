package christmas.domain;

public enum BenefitBadge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final int benefitAmount;

    BenefitBadge(String name, int benefitAmount) {
        this.name = name;
        this.benefitAmount = benefitAmount;
    }

    public static BenefitBadge findByBenefitAmountOrNull(int benefitAmount) {
        if (benefitAmount > BenefitBadge.SANTA.benefitAmount) {
            return SANTA;
        }
        if (benefitAmount > BenefitBadge.TREE.benefitAmount) {
            return TREE;
        }
        if (benefitAmount > BenefitBadge.STAR.benefitAmount) {
            return STAR;
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public int getBenefitAmount() {
        return benefitAmount;
    }
}

package test.company;

public class Delivery {
    private static final int MIN_DELIVERY_COST = 400;
    private static final int FRAGILE_SURCHARGE = 300;

    public double deliveryCost (double distance, String dimension, boolean isFragile, String workLoad) {
        if (isFragile && distance > 30) {
            throw new IllegalArgumentException("Хрупкие грузы нельзя возить на расстояние более 30 км");
        }
        double deliveryCost = distanceDeliveryCost(distance) + dimensionDeliveryCost(dimension);
        if (isFragile) {
            deliveryCost += FRAGILE_SURCHARGE;
        }
        deliveryCost *= workLoadCoefficient(workLoad);
        return Math.max(deliveryCost, MIN_DELIVERY_COST);
    }

    public int distanceDeliveryCost(double distance) {
        if (distance <= 2) return 50;
        if (distance <= 10) return 100;
        if (distance <= 30) return 200;
        return 300;
    }

    public int dimensionDeliveryCost(String dimension) {
        return switch (dimension) {
            case ("большие") -> 200;
            case ("маленькие") -> 100;
            case null, default -> throw new IllegalArgumentException("Неизвестные габариты: " + dimension);
        };
    }

    public double workLoadCoefficient(String workLoad) {
        return switch (workLoad) {
            case "очень высокая загруженность" -> 1.6;
            case "высокая загруженность" -> 1.4;
            case "повышенная загруженность" -> 1.2;
            case null, default -> 1.0;
        };
    }
}
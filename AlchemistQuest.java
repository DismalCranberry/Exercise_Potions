import java.util.*;

public class AlchemistQuest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] substancesInput = sc.nextLine().split(", ");
        Deque<Integer> substances = new ArrayDeque<>();
        for (String s : substancesInput) {
            substances.addLast(Integer.parseInt(s));
        }

        String[] crystalsInput = sc.nextLine().split(", ");
        Queue<Integer> crystals = new LinkedList<>();
        for (String s : crystalsInput) {
            crystals.offer(Integer.parseInt(s));
        }

        alchemistQuest(substances, crystals);
    }

    public static void alchemistQuest(Deque<Integer> substances, Queue<Integer> crystals) {
        Map<Integer, String> potions = new TreeMap<>(Collections.reverseOrder());
        potions.put(110, "Brew of Immortality");
        potions.put(100, "Essence of Resilience");
        potions.put(90, "Draught of Wisdom");
        potions.put(80, "Potion of Agility");
        potions.put(70, "Elixir of Strength");

        List<String> craftedPotions = new ArrayList<>();

        while (!substances.isEmpty() && !crystals.isEmpty() && craftedPotions.size() < 5) {
            int substance = substances.removeLast();
            Integer crystal = crystals.poll();
            if (crystal == null) {
                break;
            }

            int combinedEnergy = substance + crystal;

            if (potions.containsKey(combinedEnergy) && !craftedPotions.contains(potions.get(combinedEnergy))) {
                craftedPotions.add(potions.get(combinedEnergy));

            } else {

                Integer highestPossiblePotion = null;
                for (Integer energy : potions.keySet()) {
                    if (energy <= combinedEnergy && !craftedPotions.contains(potions.get(energy))) {
                        highestPossiblePotion = energy;
                        break;
                    }
                }
                if (highestPossiblePotion != null) {
                    craftedPotions.add(potions.get(highestPossiblePotion));

                    crystal -= 20;
                } else {
                    crystal -= 5;
                }
                if (crystal > 0) {
                    crystals.offer(crystal);
                }
            }
        }

        if (craftedPotions.size() == 5) {
            System.out.println("Success! The alchemist has forged all potions!");
        } else {
            System.out.println("The alchemist failed to complete his quest.");
        }

        if (!craftedPotions.isEmpty()) {
            System.out.println("Crafted potions: " + String.join(", ", craftedPotions));
        }

        if (!substances.isEmpty()) {
            List<Integer> subsList = new ArrayList<>(substances);
            Collections.reverse(subsList);
            List<String> subsStr = new ArrayList<>();
            for (Integer num : subsList) {
                subsStr.add(num.toString());
            }
            System.out.println("Substances: " + String.join(", ", subsStr));
        }


        if (!crystals.isEmpty()) {
            List<String> crysts = new ArrayList<>();
            for (Integer c : crystals) {
                crysts.add(c.toString());
            }
            System.out.println("Crystals: " + String.join(", ", crysts));
        }
    }
}

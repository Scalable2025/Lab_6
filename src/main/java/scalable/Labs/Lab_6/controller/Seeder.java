package scalable.Labs.Lab_6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import scalable.Labs.Lab_6.seed.DatabaseSeeder;

@RestController
public class Seeder {
    private final DatabaseSeeder databaseSeeder;

    @Autowired
    public Seeder(DatabaseSeeder databaseSeeder) {
        this.databaseSeeder = databaseSeeder;
    }

    @GetMapping("/seed")
    public String seed() {
        databaseSeeder.seedDatabase();
        return "Database seeded!!";
    }

    @GetMapping("/signature/{id}")
    public String signature(@PathVariable String id) {
        int total = 0;
        for (int i = 0; i < id.length(); i++) {
            total += Integer.parseInt(id.charAt(i)+"");
        }

        String result = "Mohamed + Ayman ID: " + id + "\n Total : " + total;
        return result;

    }
}

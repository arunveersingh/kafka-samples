package m3;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class AdminExample {
    public static void main(String[] args) throws Exception {

        Admin admin = Admin.create(
                Map.of("bootstrap.servers", "localhost:9092")
        );

        printAllTopics(admin);
        // create a 'booking-created' topic
        NewTopic newTopic = new NewTopic("booking-created", Optional.empty(), Optional.empty());
        admin.createTopics(Arrays.asList(newTopic));
        printAllTopics(admin);
    }

    static void printAllTopics(Admin client) throws Exception {
        // Get the actual topics
        var topicNames = client.listTopics().names().get();
        System.out.println("Topics in the cluster:");
        topicNames.forEach(System.out::println);
        System.out.println();
    }
}

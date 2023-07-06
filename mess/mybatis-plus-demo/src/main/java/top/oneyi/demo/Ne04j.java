package top.oneyi.demo;

import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;

public class Ne04j {
    public static void main(String[] args) {
        Driver driver = GraphDatabase.driver("bolt://116.62.155.98:7687", AuthTokens.basic("neo4j","RedOneForMaster"));
        Session session = driver.session();

        session.run("CREATE (n:Person {name: {name},title: {title}})",
                parameters( "name", "Arthur001", "title", "King001" ));

        StatementResult result = session.run( "MATCH (a:Person) WHERE a.name = {name} " +
                        "RETURN a.name AS name, a.title AS title",
                parameters( "name", "Arthur001" ) );

        while (result.hasNext()) {
            Record record = result.next();
            System.out.println( record.get( "title" ).asString() + " " + record.get( "name" ).asString() );
        }
        session.close();
        driver.close();
    }
}

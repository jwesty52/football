package football

class Player implements JSONFormat{

    String firstName
    String lastName
    String team
    String position

    static constraints = {
    }

    Object formatForJSON() {
        return [
                id: id
        ]
    }
}

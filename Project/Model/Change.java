package Model;

public class Change {
    private static final Change INSTANCE = new Change();
    private Change() { }
    public static Change getInstance() { return INSTANCE; }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // 이 메서드는 보통 클래스 바깥(다른 클래스)에 작성해야 한다!
    public static void main(String[] args) {
        Change po = Change.getInstance();
        po.leaveTheBuilding();
    }
}
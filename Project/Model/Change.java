package Model;

public class Change {
    private static final Change INSTANCE = new Change();
    private Change() { }
    public static Change getInstance() { return INSTANCE; }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // �� �޼���� ���� Ŭ���� �ٱ�(�ٸ� Ŭ����)�� �ۼ��ؾ� �Ѵ�!
    public static void main(String[] args) {
        Change po = Change.getInstance();
        po.leaveTheBuilding();
    }
}
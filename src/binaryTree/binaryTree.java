package binaryTree;

public class binaryTree {
    public static void main(String[] args) {
        treeManager treeManager = new treeManager(new treeNode(1, "宋江"));
        treeNode n2 = new treeNode(2, "吴用");
        treeNode n3 = new treeNode(3, "卢俊义");
        treeNode n4 = new treeNode(4, "林冲");
        treeManager.root.leftChildNode = n2;
        treeManager.root.rightChildNode = n3;
        n3.rightChildNode = n4;
        System.out.println("前序遍历：");
        treeManager.preShow();
        System.out.println();

        System.out.println("后序遍历：");
        treeManager.postShow();
        System.out.println();

        System.out.println("中序遍历：");
        treeManager.infixShow();

        System.out.println("插入节点后");
        treeNode n5 = new treeNode(5, "关胜");
        treeManager.add(n5);

        System.out.println("前序遍历：");
        treeManager.preShow();
        System.out.println();

        System.out.println("后序遍历：");
        treeManager.postShow();
        System.out.println();

        System.out.println("中序遍历：");
        treeManager.infixShow();

        System.out.println("5编号节点信息：");
        System.out.println("前序序查找，");
        System.out.println(treeManager.preSearch(3));
        System.out.println("中序查找：");
        System.out.println(treeManager.infixSearch(3));
        System.out.println("后序查找：");
        System.out.println(treeManager.postSearch(3));

        System.out.println("删除节点2");
        System.out.println(treeManager.delete(2));
        treeManager.preShow();
        System.out.println();
        System.out.println("删除节点3及其子树");
        System.out.println(treeManager.delete(3));
        treeManager.preShow();
    }
}

class treeManager {
    treeNode root;

    public treeManager(treeNode root) {
        this.root = root;
    }

    //添加节点
    public void add(treeNode node) {
        root.add(node);
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void preShow() {
        if (isEmpty()) {
            System.out.println("树为空");
            return;
        }
        root.preShow();
    }

    public void infixShow() {
        if (isEmpty()) {
            System.out.println("树为空");
            return;
        }
        root.infixShow();
    }

    public void postShow() {
        if (isEmpty()) {
            System.out.println("树为空");
            return;
        }
        root.postShow();
    }

    public treeNode preSearch(int no) {
        if (isEmpty()) {
            System.out.println("树为空");
            return null;
        }
        return root.preSearch(no);
    }

    public treeNode infixSearch(int no) {
        if (isEmpty()) {
            System.out.println("树为空");
            return null;
        }
        return root.infixSearch(no);
    }

    public treeNode postSearch(int no) {
        if (isEmpty()) {
            System.out.println("树为空");
            return null;
        }
        return root.postSearch(no);
    }

    //删除
    public boolean delete(int no) {
        if (isEmpty()) {
            System.out.println("树为空");
            return false;
        }
        return root.delete(no);
    }
}

class treeNode {
    int no;
    String name;

    treeNode leftChildNode;
    treeNode rightChildNode;

    public treeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "treeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //添加指定节点
    public void add(treeNode node) {
        if (this.no == 3) {
            this.leftChildNode = node;
        }
        if (this.leftChildNode != null) {
            this.leftChildNode.add(node);
        }
        if (this.rightChildNode != null) {
            this.rightChildNode.add(node);
        }
    }

    //前序遍历
    public void preShow() {

        System.out.println(this);
        if (this.leftChildNode != null) {
            this.leftChildNode.preShow();
        }
        if (this.rightChildNode != null) {
            this.rightChildNode.preShow();
        }
    }

    //中序遍历
    public void infixShow() {
        if (this.leftChildNode != null) {
            this.leftChildNode.infixShow();
        }
        System.out.println(this);
        if (this.rightChildNode != null) {
            this.rightChildNode.infixShow();
        }
    }

    //后序遍历
    public void postShow() {
        if (this.leftChildNode != null) {
            this.leftChildNode.postShow();
        }
        if (this.rightChildNode != null) {
            this.rightChildNode.postShow();
        }
        System.out.println(this);
    }

    //前序查找
    public treeNode preSearch(int no) {
        System.out.println("当前调用本函数");
        //如果当前节点满足，则返回当前节点
        if (this.no == no) {
            return this;
        }
        treeNode node = null;
        //再向左子树递归
        if (this.leftChildNode != null) {
            node = this.leftChildNode.preSearch(no);
        }
        //先校验左子树递归结果，不然会被右子树的结果给覆盖掉
        if (node != null) {
            return node;
        }
        //再向右子树递归
        if (this.rightChildNode != null) {
            node = this.rightChildNode.preSearch(no);
        }
        return node;
    }

    //中序查找
    public treeNode infixSearch(int no) {
        System.out.println("调用本函数");
        treeNode node = null;

        if (this.leftChildNode != null) {
            node = this.leftChildNode.infixSearch(no);
        }
        if (this.no == no) {
            return this;
        }
        //如果已找到提前返回
        if (node != null) {
            return node;
        }
        if (this.rightChildNode != null) {
            node = this.rightChildNode.infixSearch(no);
        }
        return node;
    }

    //后续查找
    public treeNode postSearch(int no) {
        System.out.println("调用本函数");
        treeNode node = null;
        if (this.leftChildNode != null) {
            node = this.leftChildNode.postSearch(no);
        }
        //左子节点找到要返回，否则会被右子节点覆盖掉
        if (node != null) {
            return node;
        }
        if (this.rightChildNode != null) {
            node = this.rightChildNode.postSearch(no);
        }
        if (this.no == no) {
            return this;
        }
        return node;
    }

    public boolean delete(int no) {
        boolean res = false;
        //左子节点不为空，删除该节点及其子树
        if (this.leftChildNode != null && this.leftChildNode.no == no) {
            if (this.leftChildNode.leftChildNode==null&&this.leftChildNode.rightChildNode==null) {
                //叶子节点直接删除
                this.leftChildNode = null;
            }else if (this.leftChildNode.leftChildNode!=null&&this.leftChildNode.rightChildNode==null){
                //非叶子节点，只删除该节点其他节点按照规则存在
                this.leftChildNode=this.leftChildNode.leftChildNode;
            }else if (this.leftChildNode.leftChildNode==null&&this.leftChildNode.rightChildNode!=null){
                this.leftChildNode=this.leftChildNode.rightChildNode;
            }else if (this.leftChildNode.leftChildNode!=null&&this.leftChildNode.rightChildNode!=null){
                this.leftChildNode=this.leftChildNode.leftChildNode;
            }
            return true;
        }
        //右子节点不为空，删除该节点及其子树
        if (this.rightChildNode != null && this.rightChildNode.no == no) {
            if (this.rightChildNode.leftChildNode==null&&this.rightChildNode.rightChildNode==null) {
                //叶子节点直接删除
                this.rightChildNode = null;
            }else if (this.rightChildNode.leftChildNode!=null){
                System.out.println("aaa");
                //非叶子节点，只删除该节点其他节点按照规则存在
                this.rightChildNode=this.rightChildNode.leftChildNode;
            }else if (this.rightChildNode.rightChildNode!=null&&this.rightChildNode.leftChildNode==null){
                System.out.println("sss");
                this.rightChildNode=this.rightChildNode.rightChildNode;
            }
            return true;
        }
        //如果没有找到，继续递归
        if (this.leftChildNode != null) {
            res = this.leftChildNode.delete(no);
        }
        if (this.rightChildNode != null) {
            res = this.rightChildNode.delete(no);
        }
        return res;
    }

}

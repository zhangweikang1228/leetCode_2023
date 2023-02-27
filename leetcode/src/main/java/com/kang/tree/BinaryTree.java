package com.kang.tree;

import java.util.*;

public class BinaryTree {


    /**
     * 144. Binary Tree Preorder Traversal
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(TreeNode root, List<Integer> list) {
        if (null == root) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    public List<Integer> preorderTraversalNoRecursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (null != node.right) {
                stack.push(node.right);
            }
            if (null != node.left) {
                stack.push(node.left);
            }
        }
        ;
        return list;
    }


    /**
     * 145. 后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(TreeNode root, List<Integer> list) {
        if (null == root) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

    /**
     * 后序遍历顺序 左-右-中 入栈顺序：中-左-右 出栈顺序：中-右-左， 最后翻转结果
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversalNoRecursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (null != node.left) {
                stack.push(node.left);
            }
            if (null != node.right) {
                stack.push(node.right);
            }


        }
        Collections.reverse(list);
        return list;
    }


    /**
     * 94.中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (null == root) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * 中序遍历顺序: 左-中-右 入栈顺序： 左-右
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalNoRecursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                list.add(current.val);
                current = current.right;
            }
        }

        return list;
    }


    /**
     * 102. Binary Tree Level Order Traversal
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        checkFun01(root, 0, resList);
        checkFun02(root, resList);
        return resList;
    }

    //DFS--递归方式
    private void checkFun01(TreeNode node, int deep, List<List<Integer>> resList) {
        if (node == null) return;
        deep++;

        if (resList.size() < deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<Integer>();
            resList.add(item);
        }
        resList.get(deep - 1).add(node.val);

        checkFun01(node.left, deep, resList);
        checkFun01(node.right, deep, resList);
    }


    //BFS--迭代方式--借助队列
    public void checkFun02(TreeNode node, List<List<Integer>> resList) {
        if (node == null) return;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(node);

        while (!que.isEmpty()) {
            List<Integer> itemList = new ArrayList<Integer>();
            int len = que.size();

            while (len > 0) {
                TreeNode tmpNode = que.poll();
                itemList.add(tmpNode.val);

                if (tmpNode.left != null) que.offer(tmpNode.left);
                if (tmpNode.right != null) que.offer(tmpNode.right);
                len--;
            }

            resList.add(itemList);
        }
    }

    /**
     * 226. Invert Binary Tree
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        // 前后序遍历都可以
        // 中序不行，因为先左孩子交换孩子，再根交换孩子（做完后，右孩子已经变成了原来的左孩子），再右孩子交换孩子（此时其实是对原来的左孩子做交换）
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        swapChildren(root);
        // 中序
/*        invertTree(root.left);
        swapChildren(root);
        invertTree(root.left);*/
        return root;
    }

    private void swapChildren(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    /**
     * 非递归
     *
     * @param root
     * @return
     */
    public TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {
                TreeNode node = deque.poll();
                swapChildren(node);
                if (node.left != null) deque.offer(node.left);
                if (node.right != null) deque.offer(node.right);
            }
        }
        return root;
    }


    /**
     * 101. Symmetric Tree
     * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        boolean res = false;

        res = compareTwoNodes(root.left, root.right);

        return res;
    }

    private boolean compareTwoNodes(TreeNode left, TreeNode right) {

        if (left != null && right == null) {
            return false;
        } else if (left == null && right != null) {
            return false;
        } else if (left == null && right == null) {
            return true;
        } else if (left.val != right.val) {
            return false;
        }
        // 比较内侧节点
        boolean inside = compareTwoNodes(left.right, right.left);
        // 比较外侧节点
        boolean outside = compareTwoNodes(left.left, right.right);

        return inside && outside;
    }

    /**
     * 104. Maximum Depth of Binary Tree
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 111. Minimum Depth of Binary Tree
     * 递归法，相比求MaxDepth要复杂点
     * 因为最小深度是从根节点到最近**叶子节点**的最短路径上的节点数量
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if (root.left == null) {
            return rightDepth + 1;
        }
        if (root.right == null) {
            return leftDepth + 1;
        }
        // 左右结点都不为null
        return Math.min(leftDepth, rightDepth) + 1;
    }


    /**
     * 222. Count Complete Tree Nodes
     * Given the root of a complete binary tree, return the number of the nodes in the tree.
     * <p>
     * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
     * <p>
     * Design an algorithm that runs in less than O(n) time complexity.
     *
     * @param root
     * @return
     */
    // 通用递归 后序遍历
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = countNodes(root.left);
        int rightDepth = countNodes(root.right);
        return leftDepth + rightDepth + 1;

    }

    /**
     * 针对完全二叉树的解法
     * <p>
     * 满二叉树的结点数为：2^depth - 1
     */
    public int countNodes2(TreeNode root) {
        if (root == null) return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 0, rightDepth = 0; // 这里初始为0是有目的的，为了下面求指数方便
        while (left != null) {  // 求左子树深度
            left = left.left;
            leftDepth++;
        }
        while (right != null) { // 求右子树深度
            right = right.right;
            rightDepth++;
        }
        if (leftDepth == rightDepth) {
            return (2 << leftDepth) - 1; // 注意(2<<1) 相当于2^2，所以leftDepth初始为0
        }
        return countNodes2(root.left) + countNodes2(root.right) + 1;
    }

    /**
     * 110. Balanced Binary Tree
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode node) {
        if (null == node) {
            return 0;
        }
        int left = getHeight(node.left);
        if (-1 == left) {
            return -1;
        }
        int right = getHeight(node.right);
        if (-1 == right) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    /**
     * 257. Binary Tree Paths
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();// 存最终结果
        if (null == root) {
            return res;
        }
        List<Integer> paths = new ArrayList<>();// 作为结果中的路径
        traversal(root, paths, res);
        return res;

    }

    private void traversal(TreeNode root, List<Integer> paths, List<String> res) {
        // 用前序遍历
        paths.add(root.val);
        if (root.left == null && root.right == null) { //找到了叶子节点
            // 输出
            StringBuilder sb = new StringBuilder(); // StringBuilder更快
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));//记录最后一个节点
            res.add(sb.toString());
            return;
        }
        // 递归和回溯是同时进行的，所以要放在同一个花括号里
        if (root.left != null) { // 左
            traversal(root.left, paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }
        if (root.right != null) { // 右
            traversal(root.right, paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }


    }

    /**
     * 迭代法
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null)
            return result;
        Stack<Object> stack = new Stack<>();
        // 节点和路径同时入栈
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {
            // 节点和路径同时出栈
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            // 若找到叶子节点
            if (node.left == null && node.right == null) {
                result.add(path);
            }
            //右子节点不为空
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
            //左子节点不为空
            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
        }
        return result;
    }


    /**
     * 100. Same Tree
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSameTreeCompare(p, q);
    }

    private boolean isSameTreeCompare(TreeNode tree1, TreeNode tree2) {

        if (tree1 == null && tree2 == null) return true;
        if (tree1 == null || tree2 == null) return false;
        if (tree1.val != tree2.val) return false;
        // 此时就是：左右节点都不为空，且数值相同的情况
        // 此时才做递归，做下一层的判断
        boolean compareLeft = isSameTreeCompare(tree1.left, tree2.left);       // 左子树：左、 右子树：左
        boolean compareRight = isSameTreeCompare(tree1.right, tree2.right);    // 左子树：右、 右子树：右
        boolean isSame = compareLeft && compareRight;                  // 左子树：中、 右子树：中（逻辑处理）
        return isSame;

    }


    /**
     * 513. Find Bottom Left Tree Value
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        // offer 在尾巴加一个元素
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (i == 0) {
                    res = poll.val;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return res;
    }

    /**
     * 112. Path Sum
     * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
     * <p>
     * A leaf is a node with no children.
     *
     * @param root
     * @param targetsum
     * @return
     */
    public boolean haspathsum(TreeNode root, int targetsum) {
        if (root == null) {
            return false;
        }
        targetsum = targetsum - root.val;

        if (root.left == null && root.right == null) {
            return targetsum == 0;
        }
        if (root.left != null) {
            boolean left = haspathsum(root.left, targetsum);
            if (left) {
                return true;// 已经找到
            }
        }
        if (root.right != null) {
            boolean right = haspathsum(root.right, targetsum);
            if (right) {
                return true;// 已经找到
            }
        }

        return false;
    }

    /**
     * 106. Construct Binary Tree from Inorder and Postorder Traversal
     */
    Map<Integer, Integer> mapFor106;  // 方便根据数值查找位置

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        mapFor106 = new HashMap<>();
        // 用map保存中序序列的数值对应位置
        for (int i = 0; i < inorder.length; i++) {
            mapFor106.put(inorder[i], i);
        }

        TreeNode treeNode = findNode(inorder, 0, inorder.length, postorder, 0, postorder.length);  // 前闭后开

        return treeNode;
    }

    private TreeNode findNode(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd) {
        // 参数里的范围都是前闭后开
        if (inBegin >= inEnd || postBegin >= postEnd) { // 不满足左闭右开，说明没有元素，返回空数
            return null;
        }
        // 找到后序遍历的最后一个元素在中序遍历中的位置
        int rootIndex = mapFor106.get(postorder[postEnd - 1]);
        // 构造结点
        TreeNode root = new TreeNode(inorder[rootIndex]);
        // 保存中序左子树个数，用来确定后序数列的个数
        int lenOfLeft = rootIndex - inBegin;
        root.left = findNode(inorder, inBegin, rootIndex, postorder, postBegin, postBegin + lenOfLeft);
        root.right = findNode(inorder, rootIndex + 1, inEnd, postorder, postBegin + lenOfLeft, postEnd - 1);


        return root;
    }

    /**
     * 654. Maximum Binary Tree
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree1(nums, 0, nums.length);
    }

    public TreeNode constructMaximumBinaryTree1(int[] nums, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex < 1) {// 没有元素了
            return null;
        }
        if (rightIndex - leftIndex == 1) {// 只有一个元素
            return new TreeNode(nums[leftIndex]);
        }
        int maxIndex = leftIndex;// 最大值所在位置
        int maxVal = nums[maxIndex];// 最大值
        for (int i = leftIndex + 1; i < rightIndex; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(maxVal);
        // 根据maxIndex划分左右子树
        root.left = constructMaximumBinaryTree1(nums, leftIndex, maxIndex);
        root.right = constructMaximumBinaryTree1(nums, maxIndex + 1, rightIndex);
        return root;
    }

    /**
     * 617.合并二叉树
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root2);
        stack.push(root1);
        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();
            node1.val += node2.val;
            if (node2.right != null && node1.right != null) {
                stack.push(node2.right);
                stack.push(node1.right);
            } else {
                if (node1.right == null) {
                    node1.right = node2.right;
                }
            }
            if (node2.left != null && node1.left != null) {
                stack.push(node2.left);
                stack.push(node1.left);
            } else {
                if (node1.left == null) {
                    node1.left = node2.left;
                }
            }
        }
        return root1;
    }

    // 递归
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        root1.val += root2.val;
        root1.left = mergeTrees2(root1.left, root2.left);
        root1.right = mergeTrees2(root1.right, root2.right);
        return root1;
    }


    /**
     * 404. Sum of Left Leaves
     * Given the root of a binary tree, return the sum of all left leaves.
     * <p>
     * A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int leftValue = sumOfLeftLeaves(root.left);
        int rightValue = sumOfLeftLeaves(root.right);
        int midValue = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            midValue = root.left.val;
        }
        int sum = midValue + leftValue + rightValue;


        return sum;
    }

    /**
     * 700. Search in a Binary Search Tree
     * You are given the root of a binary search tree (BST) and an integer val.
     * <p>
     * Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {

        if (root == null || root.val == val) {
            return root;
        }

        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }


    }

    /**
     * 98. Validate Binary Search Tree
     *
     * @param root
     * @return
     */
    // 递归
    // 简单粗暴：中序遍历完了 看看是不是单调递增
    TreeNode max;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 左
        boolean left = isValidBST(root.left);
        if (!left) {
            return false;
        }
        // 中
        if (max != null && root.val <= max.val) {
            return false;
        }
        max = root;
        // 右
        boolean right = isValidBST(root.right);

        return right;
    }

    /**
     * 530. Minimum Absolute Difference in BST
     * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
     *
     * @param root
     * @return
     */
    TreeNode pre;// 记录上一个遍历的节点
    int result = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左
        getMinimumDifference(root.left);
        // 中
        if (pre != null) {
            result = Math.min(result, root.val - pre.val);
        }
        pre = root;
        // 右
        getMinimumDifference(root.right);
        return result;
    }

    /**
     * 501. Find Mode in Binary Search Tree
     *
     * @param root
     * @return
     */

    ArrayList<Integer> resListFor501;
    int maxCountFor501;
    int countFor501;
    TreeNode preFor501;

    public int[] findMode(TreeNode root) {

        resListFor501 = new ArrayList<>();
        maxCountFor501 = 0;
        countFor501 = 0;
        preFor501 = null;
        findModeFor501(root);

        int[] res = new int[resListFor501.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resListFor501.get(i);
        }

        return res;
    }

    private void findModeFor501(TreeNode root) {
        if (root == null) {
            return;
        }
        // 左
        findModeFor501(root.left);
        // 中
        int rootValue = root.val;
        // 技术
        if (preFor501 == null || rootValue != preFor501.val) {
            countFor501 = 1;
        } else {
            countFor501++;
        }
        // 更新结果及maxCount
        if (countFor501 > maxCountFor501) {
            resListFor501.clear();
            resListFor501.add(rootValue);
            maxCountFor501 = countFor501;
        } else if (countFor501 == maxCountFor501) {
            resListFor501.add(rootValue);
        }
        preFor501 = root;
        //右
        findModeFor501(root.right);
    }


    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) { // 递归结束条件
            return root;
        }

        // 后序遍历
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null) { // 若未找到节点 p 或 q
            return null;
        } else if (left == null && right != null) { // 若找到一个节点
            return right;
        } else if (left != null && right == null) { // 若找到一个节点
            return left;
        } else { // 若找到两个节点
            return root;
        }
    }

    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor2(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor2(root.right, p, q);
        }
        return root;
    }

    /**
     * 701. Insert into a Binary Search Tree
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 如果当前节点为空，也就意味着val找到了合适的位置，此时创建节点直接返回。
        if (root == null) {
            return new TreeNode(val);
        }
        // 递归创建左子树
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }
        // 递归创建右子树
        if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }


    /**
     * 450. Delete Node in a BST
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key == root.val) {
            // 叶子结点，左右子树都为空
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right == null) { // 左不为空，右为空
                return root.left;
            } else if (root.left == null && root.right != null) { // 左为空，右不为空
                return root.right;
            } else { // 左不空，右不空， 该节点左子树移动到其右子树最左侧的结点的左边
                TreeNode current = root.right;
                while (current.left != null) {
                    current = current.left;
                }
                current.left = root.left;
                root = root.right;
                return root;
            }

        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    /**
     * 669. Trim a Binary Search Tree
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {

        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;

    }


    /**
     * 108. Convert Sorted Array to Binary Search Tree
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        // 数组内左闭右开
        return sortedArrayToBST(nums, 0, nums.length);
    }

    private TreeNode sortedArrayToBST(int[] nums, int left, int right) {

        if (left >= right) {
            return null;
        }
        if (right - left == 1) {
            return new TreeNode(nums[left]);
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid);
        root.right = sortedArrayToBST(nums, mid + 1, right);
        return root;
    }


    /**
     * 538. Convert BST to Greater Tree
     * @param root
     * @return
     */
    int sumFor538;
    public TreeNode convertBST(TreeNode root) {
        sumFor538 = 0;
        convertBST1(root);
        return root;
    }

    // 按右中左顺序遍历，累加即可
    public void convertBST1(TreeNode root) {
        if (root == null) {
            return;
        }
        convertBST1(root.right);
        sumFor538 += root.val;
        root.val = sumFor538;
        convertBST1(root.left);
    }

}

package lrucache;

final class DoublyLinkedList<K, V> {
    private final Node<K, V> head;
    private final Node<K, V> tail;

    DoublyLinkedList() {
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    void addToHead(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    Node<K, V> removeTail() {
        Node<K, V> node = tail.prev;
        if (node == head) {
            return null;
        }
        removeNode(node);
        return node;
    }

    void moveToHead(Node<K, V> node) {
        removeNode(node);
        addToHead(node);
    }

    void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

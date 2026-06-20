//узел дерева
class ContactNode{
  String name;
  String phone;
  ContactNode left,right; //ссылки на левые и правые узлы

  ContactNode(String name, String phone){
      this.name = name;
      this.phone = phone;
  }
} 

class PhoneBook{
  private ContactNode root;

  public void add(String name, String phone){
    root = addTo(root,name,phone);
  }

  private ContactNode addTo(ContactNode node, String name, String phone){
    if (node == null) return new ContactNode(name,phone);

    int compare = name.compareTo(node.name);
    /*compareTo возвращает разницу числовых кодов между первыми несовподающими символами
    если символы совпали, но одна строка длиннее другой, возвращает разницу длины*/

    if (compare<0) node.left = addTo(node.left, name,phone);
    else if(compare>0) node.right = addTo(node.right,name,phone);
    return node;
  }

  public String findPhone(String name){
    ContactNode result = find(root,name);
    return result != null ? result.phone: "Контакт не найден";
    // тернарный оператор | условие ? если true : если false
  }

  private ContactNode find(ContactNode node, String name){
    if(node == null) return null;

    int compare = name.compareTo(node.name);
    if (compare == 0) return node;
    if (compare < 0) return find(node.left, name);
    return find(node.right, name);
  }
}

  public static void main(String[] args) {
      PhoneBook phones = new PhoneBook();
      phones.add("Иван", "123-456");
      phones.add("Петр", "789-012");
      phones.add("Анна", "345-678");
        
      System.out.println(pb.findPhone("Иван"));
      System.out.println(pb.findPhone("Алекс"));
  }

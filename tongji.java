//ͳ�Ƶ��ʳ��ֵ�Ƶ��
HashMap<String, Integer> map = new HashMap();
for (String str : sortword) {
    if (!map.containsKey(str)) {//��str������,
        map.put(str, 1);
    } else {
        //������c��ֵ���Ҽ�1
        map.put(str, map.get(str) + 1);
    }
    //System.out.println(str + "���ֵĴ���Ϊ:" + map.get(str)+"��");
}
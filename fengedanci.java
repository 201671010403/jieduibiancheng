package a.a;
 					// spilrStr1Ϊ�ļ�������
                   //String spilrStr1 = String.valueOf(text);
                    //����","�ָ���ַ�����(��".")
                    String spilrStr2 = StringUtils.join(word1);
                    //��"."�ָ��ַ�����(ֻʣ�ո�)
                    String[] word2 = spilrStr2.split("\\.");
                    //ת��Ϊ�ַ���
                    String spilrStr3 = StringUtils.join(word2);
                    //���ݿո�ֿ�
                    String[] word3 = spilrStr3.split(" ");
                    String spilrStr4 = StringUtils.join(word3, " ");
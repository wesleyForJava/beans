package com.wesley.bean.test.stack;

public class Reverse{
    private String input;
    private String output;
    public Reverse(String in){          //构造函数
        input=in;
    }
    public String doRev(){              //反转功能函数 返回值为String
        int stackSize=input.length();
        StackY theStack=new StackY(stackSize);
        for(int i=0;i<input.length();i++)       //将输入的字符串压栈处理
        {
            char ch=input.charAt(i);
            theStack.push(ch);
        }
        output="";
        while(!theStack.isEmpty()){             //将栈中的字符逐一弹出，并被ouput接收，达到反转效果
            char c=theStack.pop();
            output+=c;
        }
        return output;
    }
}


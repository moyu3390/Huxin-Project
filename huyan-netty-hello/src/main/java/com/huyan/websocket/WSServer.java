package com.huyan.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/** 
  * @author ���� 
  * @version ����ʱ�䣺2019��1��17�� ����4:48:13 
  * @Description:
  */
public class WSServer {
	public static void main(String[] args) throws Exception{
		
		EventLoopGroup mainGroup = new NioEventLoopGroup();
		EventLoopGroup subGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(mainGroup,subGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new WSServerInitialzer());
			
			ChannelFuture future = server.bind(8088).sync();
			
			future.channel().closeFuture().sync();
		} finally {
			mainGroup.shutdownGracefully();
			subGroup.shutdownGracefully();
		} 
	}
}
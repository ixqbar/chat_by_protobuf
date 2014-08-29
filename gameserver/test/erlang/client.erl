-module(client).
-export([test/2,connect/1,send/3,wait_reply/0]).

test(I,S) ->
	if I =< S ->
		spawn(fun() ->connect(I) end),
		test(I+1,S)
	end.

connect(Index) ->
	case gen_tcp:connect("127.0.0.1",8088,[binary,{packet,4},{reuseaddr,true}]) of
		{ok,Socket} -> 
			send(Socket, ["hello, ", integer_to_list(Index)], Index);
		{error,Reason} ->
			io:format("connect failed:~p~n",[Reason])
	end.

send(Socket,Message, Index) ->
	gen_tcp:send(Socket, Message),
	Reply = wait_reply(),
	io:format("Reply ~p = ~p~n",[Index,Reply]),
	send(Socket, Message, Index).

wait_reply() ->
    receive
		Reply ->			
	    	{value, Reply}
    after 100000 ->
	    	timeout
    end.  


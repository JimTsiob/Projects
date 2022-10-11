-- Dimitris Tsiompikas 3180223
-- Antwnhs Detshs 3190054
-- Petros Tsotsi 3180193


LIBRARY IEEE;
USE ieee.std_logic_1164.all;
USE IEEE.std_logic_signed.all;


ENTITY ALU IS

PORT(
		input1,input2 : in std_logic_vector(15 DOWNTO 0);
		operation : in std_logic_vector(3 DOWNTO 0);
		output : out std_logic_vector(15 DOWNTO 0);
		addcout : out std_logic;
		subcout : out std_logic
);

END ALU;

ARCHITECTURE logic_structural OF ALU IS


-- include full adder

COMPONENT FULLADDER_16 IS
PORT( in1,in2 : in std_logic_vector(15 DOWNTO 0);
		cin : in std_logic;
		sum : out std_logic_vector(15 DOWNTO 0);
		cout , overflow : out std_logic
);
END COMPONENT;

-- include full subtractor

COMPONENT FULL_SUB_16 IS
PORT(
		A,B : in std_logic_vector(15 DOWNTO 0);
		C : in std_logic;
		diff : out std_LOGIC_VECTOR(15 DOWNTO 0);
		bout : out std_logic
);
END COMPONENT;


-- include and 16 bit

COMPONENT AND_16 IS

PORT (in1,in2 : in std_logic_vector(15 DOWNTO 0);
		output : out std_logic_vector(15 DOWNTO 0)
);

END COMPONENT;

-- include or 16 bit

COMPONENT OR_16 IS

PORT(
	in1,in2 : in std_logic_vector(15 DOWNTO 0);
	output : out std_logic_vector(15 DOWNTO 0)	
);

END COMPONENT;

-- include greater equal 16 bit

COMPONENT GEQ_16 IS

PORT(
		in1 : in std_logic_vector(15 DOWNTO 0);
		output : out std_logic_vector(15 DOWNTO 0)
);

END COMPONENT;

-- include not 16 bit

COMPONENT NOT_16 IS

PORT(in1 : in std_logic_vector(15 DOWNTO 0);
	  output : out std_logic_vector(15 DOWNTO 0)	
);

END COMPONENT;

-- include 16 bit multiplexer 8 to 1

COMPONENT MUX8_1_16BIT IS

PORT (ADD_RES,SUB_RES,AND_RES,OR_RES,GEQ_RES,NOT_RES: IN STD_LOGIC_VECTOR(15 DOWNTO 0);
		SEL : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
		OUT1 : OUT STD_LOGIC_VECTOR(15 DOWNTO 0));
		
		
END COMPONENT;

	

SIGNAL zero : std_logic := '0';
SIGNAL add_result,sub_result,and_result,or_result,geq_result,not_result : std_logic_vector(15 DOWNTO 0);

BEGIN
	add : FULLADDER_16 port map (input1,input2,zero,add_result,addcout);
	sub : FULL_SUB_16 port map (input1,input2,zero,sub_result,subcout);
	and_op : AND_16 port map (input1,input2,and_result);
	or_op : OR_16 port map (input1,input2,or_result);
	geq_op : GEQ_16 port map (input1,geq_result);
	not_op : NOT_16 port map (input1,not_result);
	
	
	mux : MUX8_1_16BIT port map (add_result,
										  sub_result, -- subtraction
										  and_result,
										  or_result,
										  geq_result,
										  not_result,
										  operation,
										  output
										  
	);

END logic_structural;
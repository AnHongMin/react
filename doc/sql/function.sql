CREATE OR REPLACE FUNCTION "SELECTNUM"         (sVal in varchar2)
return varchar2
is
y varchar2(20);
n  int;

begin
    n :=0;
    LOOP
         n := n + 1;

          if ((ASCII(substr(sVal,n,1))) > 47) and ((ASCII(substr(sVal,n,1))) < 58)  then
				     y :=  y  || substr(sVal,n,1) ;
		  	 end if;



          Exit when n=Length(sVal);
    END LOOP;


return y;

end;


CREATE OR REPLACE FUNCTION XFormat(gubun in varchar2, X in varchar2)
return varchar2
is
y varchar2(20);
STEL1 VARCHAR2(10);
STEL2 VARCHAR2(10);
STEL3 VARCHAR2(10);
STEMP VARCHAR2(10);
NCNT  NUMBER;

begin

y := X;
if gubun = '1' then                 -- 우편번호
   if Length(X) = 6  then
         y := substr(X,1,3) || '-' || substr(X,4,6);
   end if;
end if;
if gubun = '2' then                 -- 주민/사업 번호
   if Length(X) = 10  then
         y := substr(X,1,3) || '-' || substr(X,4,2) || '-' || substr(X,6,5) ;
   end if;
   if Length(X) = 13  then
         y := substr(X,1,6) || '-' || substr(X,7,7);
   end if;
end if;
if gubun = '3' then                 -- 전화번호
      --   y := X ;
	  
	  IF LENGTH(X) < 7 OR LENGTH(X) > 12 THEN
	  	 Y := X;
	  ELSE
		  
		  STEL3 := SUBSTR(X, LENGTH(X)-3, 4) ;
		  STEMP := SUBSTR(X, 1, LENGTH(X)-4) ;
		  
		  IF (SUBSTR(STEMP,1,1) <> '0') THEN
		  	  IF (LENGTH(X) > 8) THEN
			 	  Y := X;
			  ELSE
			 	  Y := '02-'||STEMP||'-'||STEL3;
				  
			  END IF;
		  ELSE
		  	  IF LENGTH(STEMP) <=4 THEN
			  	  Y := STEMP||'-'||STEL3 ; 
			  END IF;
			  
			  IF SUBSTR(STEMP,1,2) = '02' THEN
			  	  NCNT := 2;
			  ELSIF SUBSTR(STEMP,1,2) = '01' THEN
			  	  NCNT := 3;
			  ELSE
			  	  NCNT := 3;
			  END IF;
			  
			  STEL1 := SUBSTR(STEMP, 1,NCNT) ;
			  STEL2 := SUBSTR(STEMP, NCNT+1, LENGTH(STEMP));
			  
			  IF LENGTH(STEL2) > 4 THEN
			  	 Y := X;
			  ELSE
			  	 Y := '0'||TO_CHAR(TO_NUMBER(STEL1))||'-'||STEL2||'-'||STEL3;
			  END IF ;
		  END IF;
		  
	  END IF ;
	  

end if;

if gubun = '4' then                 -- 전화번호
   if Length(X) = 12  then
         y := '0' || LTrim(substr(X,1,4),'0') || '-' || LTrim(substr(X,5,4),'0') || '-' || LTrim(substr(X,9,4),'0');
   end if;
end if;

return y;
end;
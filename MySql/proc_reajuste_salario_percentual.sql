USE helpr;

SELECT * FROM helpr.cargo;

DELIMITER //

CREATE PROCEDURE reajuste_salario(piso DECIMAL)
BEGIN
	UPDATE cargo SET salario = salario + (salario * piso/100);
END//

CALL reajuste_salario(20);

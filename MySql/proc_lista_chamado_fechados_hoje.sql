USE helpr;

SELECT * FROM helpr.chamado;

DELIMITER //

CREATE PROCEDURE listarChamadosFechadosNoDia(SelectedDate DATE)
BEGIN
		SELECT * FROM helpr.chamado WHERE data_fechamento = SelectedDate;
END //

CALL listarChamadosFechadosNoDia('2022-12-07')
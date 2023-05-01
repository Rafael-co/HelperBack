use helpr;

DELIMITER $$

CREATE PROCEDURE proc_alteracao_imagem()
BEGIN
	UPDATE usuarios SET foto = "https://raw.githubusercontent.com/F-Gabriel-Braga/app-collaborators/master/src/assets/images/avatar.png" 
    WHERE foto IS NULL 
    AND 
    dtype = "funcionario";
END$$

DELIMITER ;

CALL proc_alteracao_imagem();
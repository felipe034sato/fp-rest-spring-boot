package br.com.felipe034sato.mapper;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {
    //Mapear entidade para Vo e vice versa(objeto de origem para o de destino)

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    //Metodo generico para mapar o metodo de origem para o de destino
    //Person --- PersonDTO
    public static <O,D> D parseObject (O origin, Class<D> destination){
        return mapper.map(origin, destination); //Take this object and create another one with the same data
    }

    public static <O,D> List<D> parseListObject (List<O> origin, Class<D> destination){

        //Cria uma lista temporaria para interar e retornar
        List<D> destinationObject = new ArrayList<D>();
        //Integra com os objetos adicionais e converte one by one e depois devolve essa lista
        for(Object o : origin){
//            var a = mapper.map(o,destination);
//            destinationObject.add(a);
            destinationObject.add(mapper.map(o, destination));
        }
        return destinationObject;
    }
}

require "TP1/Prototyped"
require "TP1/Singleton_Module_Behaviour"

module TP

  class CopyConstructor
    def build(new_object)
      new_object.prototypes.first.clone
    end

    def arity
      0
    end
  end

  class HashConstructor
    def build(new_object, hash)
      hash.each{ |key, value| new_object.send("#{key}=", value) }
      new_object
    end

    def arity
      1
    end
  end

  class ProcConstructor

    attr_accessor :constructor

    def initialize(block)
      self.constructor = block
    end

    def build(new_object, *args)
      self.constructor.call(new_object, *args)
      new_object
    end

    def arity
      self.constructor.arity - 1
    end
  end

  class DoEndConstructor

    attr_accessor :do_end_block

    def initialize(&block)
      self.do_end_block = block
    end

    def build(new_object, *args)
      new_object.instance_exec *args, &self.do_end_block
      new_object
    end

    def arity
      self.do_end_block.arity
    end

  end

  class WithPropertiesConstructor

    attr_accessor :base_properties

    def initialize(properties)
      self.base_properties = properties
    end

    def arity
      self.base_properties.length
    end

    def build(new_object, *args)
      new_hash = self.base_properties.zip(args).to_h
      HashConstructor.new.build(new_object,new_hash)
    end


  end

end
